     ______    ___  ___ ___  ____    __  ____     ___  ___    _____
    |      T  /  _]|   T   T|    \  /  ]|    \   /  _]|   \  / ___/
    |      | /  [_ | _   _ ||  o  )/  / |  D  ) /  [_ |    \(   \_ 
    l_j  l_jY    _]|  \_/  ||   _//  /  |    / Y    _]|  D  Y\__  T
      |  |  |   [_ |   |   ||  | /   \_ |    \ |   [_ |     |/  \ |
      |  |  |     T|   |   ||  | \     ||  .  Y|     T|     |\    |
      l__j  l_____jl___j___jl__j  \____jl__j\_jl_____jl_____j \___j
                                                               
[![Clojars Project](http://clojars.org/tempcreds/latest-version.svg)](http://clojars.org/tempcreds)

Leiningen dependency:

    [tempcreds "1.1"]
    
Usage:

    (require '[tempcreds :refer [tempcreds]])
    (tempcreds profile serialno mfatoken)
    
Where `profile` is the section of your `~/.aws/credentials` file with the non-MFA credentials you're going to use for the request. `serialno` is the serial number of your MFA device, like `arn:aws:iam::123123123123123:mfa/bob.builder`. And `mfatoken` is the current six-digit code showing on your MFA device.
