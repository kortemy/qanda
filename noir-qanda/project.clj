(defproject qanda "0.1.0-SNAPSHOT"
            :description "FIXME: write this!"
            :dependencies [[org.clojure/clojure "1.4.0"]
                           [noir "1.3.0-beta3"]  
                           [org.clojure/java.jdbc "0.2.3"]        ;; jdbc 
                           [mysql/mysql-connector-java "5.1.6"]]
            :plugins [[lein-localrepo "0.4.0"]]
            :main qanda.server)

