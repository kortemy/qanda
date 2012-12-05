(ns qanda.db.db-core)

(defn db [] {:classname "com.mysql.jdbc.Driver"
               :subprotocol "mysql"
               :subname "//127.0.0.1:3306/qanda"
               :user "root"
               :password "root"})