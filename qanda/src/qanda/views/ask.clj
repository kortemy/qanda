(ns qanda.views.ask
  (use noir.core
       hiccup.core
       hiccup.page-helpers
       hiccup.form-helpers)
  (:require [qanda.views.layout :as layout])
  (:require [qanda.db.questions-handler :as qh])
  (:require [noir.response :as response]))

(defpartial text-block []
  [:p "Welcome to " (link-to "/" "QandA")", your Questions and Answers site! 
Here you can aks us questions from different fields, and our admins will try to answer
as soon as possible."]
  [:p "In our databases there are currently " [:span.blue (count(qh/get-answered))] " answered questions."]
  [:p [:span.blue (count(qh/get-unanswered))] " questions are waiting for an answer."])

(defpartial submit-form [{:keys [name type text]}]
  (label {:class "left"} "name" "Your name: ")
  (text-field {:class "stylized"} "name" name)
  (label {:class "left"} "type" "Category: ")
  (drop-down {:class "stylized"} "type" ["Hardware" "Software" "Internet" "Other"])
  (label {:class "left"} "question" "Your Question: ")
  [:br]
  (text-area {:class "stylized"} "question" text))

(defn store-redirect [{:keys [name type question]}]
  (qh/insert name type question)
  (response/redirect "/thanks"))

(defpage "/" {:as question}
	(layout/main
   (text-block)
   [:div.hline-bottom ]
   [:h3.blue "Ask your question here:"]
	  (form-to {:class "form"} [:post "/"]
            (submit-form question)
            [:br]
            (label {:class "left"} "name" "&nbsp;")
            (submit-button {:class "ask-button"} "Ask!")
            [:div.clear])))

(defpage [:post "/"] {:as question}
  (store-redirect question))

(defpage "/thanks" []
	(layout/main
	 [:div 
   [:h3 "Thank you for your question!"]
   [:p "We'll try to supply you with an answer as soon as possible."]
   [:div (link-to {:class "answer-link"} "/questions" "View answered questions")]
    [:div.clear]]))
