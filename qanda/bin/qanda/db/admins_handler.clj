(ns qanda.db.admins-handler
  (:require [clojure.java.jdbc :as sql])
  (:require [qanda.db.db-core :as core]))

(defn admins-read []
  (sql/with-connection (core/db)
    (sql/with-query-results res
      ["SELECT * FROM admins"]
      (into [] res))))

(defn admin-login? [username password]
  (sql/with-connection (core/db)
    (sql/with-query-results res
      ["SELECT * FROM admins WHERE username=? AND password=?" username password]
      (> (count res) 0))))

(defn admin-get-by-uid [uid]
  (sql/with-connection (core/db)
    (sql/with-query-results res
      ["SELECT * FROM admins WHERE uid=?" uid]
      (first res))))

(defn admin-get-by-username [username]
  (sql/with-connection (core/db)
    (sql/with-query-results res
      ["SELECT * FROM admins WHERE username=?" username]
      (first res))))