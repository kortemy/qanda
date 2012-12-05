(ns qanda.db.questions-handler
  (:require [clojure.java.jdbc :as sql])
  (:require [qanda.db.db-core :as core]))

(defn get-all []
  (sql/with-connection (core/db)
    (sql/with-query-results res
      ["SELECT * FROM questions"]
      (into [] res))))

(defn get-answered []
  (sql/with-connection (core/db)
    (sql/with-query-results res
      ["SELECT * FROM questions WHERE uid is not null"]
      (into [] res))))

(defn get-unanswered []
  (sql/with-connection (core/db)
    (sql/with-query-results res
      ["SELECT * FROM questions WHERE uid is null"]
      (into [] res))))

(defn current-time [] 
  (.format (java.text.SimpleDateFormat. "dd. MM. HH:mm") (java.util.Date.))) 

(defn insert [author type question]
  (sql/with-connection (core/db)
    (sql/transaction
		  (sql/insert-values
		    :questions
		    [:qid :author :type :date :question]
		    [(+ 1 (count (get-all))) author type (current-time) question]))))

(defn update [qid uid answer]
  (sql/with-connection (core/db)
    (sql/transaction
		  (sql/update-values
		    :questions
      ["qid=?" qid]
		    {:uid uid :answer answer}))))

(defn get-by-id [qid]
  (sql/with-connection (core/db)
    (sql/with-query-results res
      ["SELECT * FROM questions WHERE qid=?" qid]
      (first res))))

