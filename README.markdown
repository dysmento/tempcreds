 _                                         _     
| |                                       | |    
| |_ ___ _ __ ___  _ __   ___ _ __ ___  __| |___ 
| __/ _ \ '_ ` _ \| '_ \ / __| '__/ _ \/ _` / __|
| ||  __/ | | | | | |_) | (__| | |  __/ (_| \__ \
 \__\___|_| |_| |_| .__/ \___|_|  \___|\__,_|___/
                  | |                            
                  |_| 
                                                               
[![Clojars Project](http://clojars.org/tempcreds/latest-version.svg)](http://clojars.org/tempcreds)

Leiningen dependency:

    [tempcreds "1.1"]
    
Usage:

    (require '[tempcreds :refer [tempcreds]])
    (tempcreds profile serialno mfatoken)
    
Where `profile` is the section of your `~/.aws/credentials` file with the non-MFA credentials you're going to use for the request. `serialno` is the serial number of your MFA device, like `arn:aws:iam::123123123123123:mfa/bob.builder`. And `mfatoken` is the current six-digit code showing on your MFA device.

