(ns qanda.views.about
  (:require [qanda.views.layout :as common])
  (:require [clojure.java.jdbc :as sql])
  (:use [noir.core :only [defpage]]))

(defn method [param]
  (str "Write " param))

(def mysql-db {:classname "com.mysql.jdbc.Driver"
               :subprotocol "mysql"
               :subname "//localhost:3306/mysql"
               :user "root"
               :password "root"})

(defn get-users []
  (println "usao sam")
  (sql/with-connection mysql-db
    (sql/with-query-results res
      ["SELECT * FROM `clj_users` WHERE 1"]
      (into ["korisnici"] res))))

(defn insert-record-user  []
  (sql/insert-record
    :administrators
    {:id 3 :username "Pera" :password "pera"}))

(defn insert-rows-fruit  []
  (sql/insert-rows
    :questions
    [1 "Apple" "red" nil]
    [2 "Banana" "yellow" "answer"]
    [3 "Peach" "fuzzy" nil]
    [4 "Orange" "juicy" nil]))

(defn db-sql-exception
  "Demonstrate an sql exception"
  []
  (sql/with-connection mysql-db
    (sql/transaction
     (sql/insert-values
      :questions
      [:id :type :question :answer]
      [1 "other" "red" nil]
      [2 "software" "yellow" "answer"]
      [3 "other" "fuzzy" nil]
      [4 "internet" "juicy" nil]))))

(defn db-write  []
		(sql/with-connection mysql-db
		    (sql/transaction
		     (db-sql-exception)))
		  nil)


(defpage "/login" []
         (common/layout
           [:h1 "hello from login"]
           [:br]
           [:h2 (method "some text")]
           [:div (str db-write)]
           [:span "kurac"]))


