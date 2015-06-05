     ______    ___  ___ ___  ____    __  ____     ___  ___    _____
    |      T  /  _]|   T   T|    \  /  ]|    \   /  _]|   \  / ___/
    |      | /  [_ | _   _ ||  o  )/  / |  D  ) /  [_ |    \(   \_ 
    l_j  l_jY    _]|  \_/  ||   _//  /  |    / Y    _]|  D  Y\__  T
      |  |  |   [_ |   |   ||  | /   \_ |    \ |   [_ |     |/  \ |
      |  |  |     T|   |   ||  | \     ||  .  Y|     T|     |\    |
      l__j  l_____jl___j___jl__j  \____jl__j\_jl_____jl_____j \___j
                                                               
[![Clojars Project](http://clojars.org/tempcreds/latest-version.svg)](http://clojars.org/tempcreds)

Leiningen dependency:

    [tempcreds "1.2"]
    
Usage:

```clojure
(require '[tempcreds :refer [tempcreds]])
(tempcreds profile serialno mfatoken)
```
    
Where `profile` is the section of your `~/.aws/credentials` file with the non-MFA credentials you're going to use for the request. `serialno` is the serial number of your MFA device, like `arn:aws:iam::123123123123123:mfa/bob.builder`. And `mfatoken` is the current six-digit code showing on your MFA device.

# What Is This Even About?
I read [this awesome blog post][1] by Josh Johnson all about how to use [boot][2] for scripting, and I wanted to create a small project to put all the pieces together and try it myself. So this project is a small thing that is also useful (I need temporary AWS credentials practially every day) _and_ it was a way to learn how to make useful things with boot.

# So What All Is Here?
With boot, you can write shell scripts in [clojure][3] simply by having boot installed and using this as your shebang line:

    #!/usr/bin/env boot
    
So at the root of this project is a file called `tempcreds` and it is the shell script I made to do this temporary credentials thing. I could put all the code in this file if I wanted, but boot lets you declare dependencies right in the script, which means you can keep your scripts from getting unwieldy by publishing your own code as artifacts into a maven-style repository, and then declare that as a dependency in the script. That's what I've done here.

What's awesome is that if you want to redistribute one of your scripts, you don't have to include all the other files. In this case, I could send someone just the `tempcreds` file, and as long as I can assume they have boot installed plus a JDK, the script will take care of the whole rest of the dependency tree.

Since I want to build and deploy an artifact, I have a `build.boot` file to help facilitate that. Most of `build.boot` is about defining the project object model (also known as the [maven pom file][4]) and declaring the settings for the repository I want to publish to. To build the artifact, just run this command from the project directory:

    boot build

You probably won't be publishing any artifacts from this project, but in case you're curious, the way this would work is you put the secret environment variables into your environment (`CLOJARS_USERNAME`,`CLOJARS_PASSWORD`,`GPG_PASSPHRASE`) and then use this command:

    boot push -f target/tempcreds-1.2.jar

The source code (in its entirety) is in `src/tempcreds.clj`. Of course, your project could be arbitrarily large. Mine is little, that's all.

[1]:https://lionfacelemonface.wordpress.com/2015/04/11/advanced-boot-scripting/
[2]:http://boot-clj.com/
[3]:http://clojure.org/
[4]:https://maven.apache.org/guides/introduction/introduction-to-the-pom.html
