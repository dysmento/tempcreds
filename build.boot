(set-env! :dependencies
          '[[com.amazonaws/aws-java-sdk-sts "1.9.39"]
            [boot/core "2.0.0"]
            [clojure-ini "0.0.2"]
            [org.clojure/clojure "1.6.0"]]
          :source-paths #{"src"}
          :repositories
          #(conj % ["clojars-upload"
                    {:url "https://clojars.org/repo"
                     :username (System/getenv "CLOJARS_USERNAME")
                     :password (System/getenv "CLOJARS_PASSWORD")}]))

(task-options!
 pom {:project 'tempcreds
      :url "https://bitbucket.org/inindca/stuff"
      :version "1.1"
      :description "A small library for setting local temporary credentials for AWS using an MFA device."
      :license {"Apache License" "http://opensource.org/licenses/Apache-2.0"}}
 aot {:all true}
 push {:gpg-sign true
       :repo "clojars-upload"
       :gpg-user-id "zach.thomas@gmail.com"
       :gpg-passphrase (System/getenv "GPG_PASSPHRASE")})

(deftask build
  "Build the tempcreds library jar."
  []
  (comp (aot) (pom) (jar)))
