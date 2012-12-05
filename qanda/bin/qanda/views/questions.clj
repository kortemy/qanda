(ns qanda.views.questions
  (use noir.core
       hiccup.core
       hiccup.page-helpers)
  (:require [qanda.views.layout :as layout])
  (:require [qanda.db.questions-handler :as qh])
  (:require [qanda.db.admins-handler :as ah])
  (:require [noir.response :as response]))

(defpartial question-block [{:keys [author question answer date type uid] :as post}]
	(when post
	  [:li.post.hline-top
    [:div.left (image {:class "icon"} (str "/img/" type ".png"))]
	   [:div.headline "Asked on "
	    [:span.date.blue date] " by "
	    [:span.author.blue author] " in category "
      [:span.type.blue type] ":"]
    [:div.clear]
     [:div.text question]
     [:div "Answered by " [:span.blue (:fullname (ah/admin-get-by-uid uid))] ":"]
	   [:div.answer answer]]))

(defn build-page [questions]
  (layout/main
    [:h3 "Answered questions:"]
    [:ul.questions.hline-bottom
     (map question-block questions)]))

(defpage "/questions" []
  (build-page (qh/get-answered)))