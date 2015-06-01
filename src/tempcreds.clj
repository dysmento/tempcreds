(ns tempcreds
  (:require [clojure-ini.core :as ini])
  (:import (com.amazonaws.services.securitytoken AWSSecurityTokenServiceClient)
          (com.amazonaws.services.securitytoken.model GetSessionTokenRequest)))

(defn credsfilepath [] (str (System/getProperty "user.home") "/.aws/credentials"))

(defn makesessionrequest
  [serialno tokencode]
  (-> (GetSessionTokenRequest.)
      (.withSerialNumber serialno)
      (.withTokenCode tokencode)
      (.withDurationSeconds (int 43200))))

(defn readcredentials [profile]
  (let [config (ini/read-ini (credsfilepath) :keywordize? true)]
    (com.amazonaws.auth.BasicAWSCredentials.
     (get-in config [(keyword profile) :aws_access_key_id])
     (get-in config [(keyword profile) :aws_secret_access_key]))))

(defn items [sectionmap]
  (apply str (map #(str (name %) " = " (% sectionmap) "\n") (keys sectionmap))))

(defn maptoconfig [configmap]
  (apply str (map #(str "[" (name %) "]" "\n" (items (% configmap)) "\n") (keys configmap))))

(defn writecreds! [credsmap]
  (spit (credsfilepath) (maptoconfig credsmap)))

(defn sectionmap [awscredentials]
  (let [accesskey (.getAccessKeyId awscredentials)
        secretkey (.getSecretAccessKey awscredentials)
        sessiontoken (.getSessionToken awscredentials)]
    {:region "us-east-1" :aws_access_key_id accesskey :aws_secret_access_key secretkey :aws_session_token sessiontoken}))

(defn tempcreds [profile serialno tokencode]
  (let [sessionrequest (makesessionrequest serialno tokencode)
        credentials (readcredentials profile)
        config (ini/read-ini (credsfilepath) :keywordize? true)]
    (try
      (writecreds! (assoc config :default (sectionmap
                                             (-> (AWSSecurityTokenServiceClient. credentials)
                                                 (.getSessionToken sessionrequest)
                                                 (.getCredentials)))))
      (println "Temporary credentials established, nice and fresh.")
      (catch com.amazonaws.AmazonServiceException e (prn (.getMessage e))))))
