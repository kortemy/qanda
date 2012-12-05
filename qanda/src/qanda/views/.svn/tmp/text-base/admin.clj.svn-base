(ns qanda.views.admin
  (use noir.core
       hiccup.core
       hiccup.page-helpers
       hiccup.form-helpers)
  (:require [qanda.views.layout :as layout])
  (:require [qanda.db.admins-handler :as ah])
  (:require [qanda.db.questions-handler :as qh])
  (:require [noir.session :as session])
  (:require [noir.response :as response]))

;; Login page

(defpartial admin-login [{:keys [username password]}]
  (label {:class "left"}  "username" "Username: ")
  (text-field {:class "stylized"} "username" username)
  [:br]
  (label {:class "left"} "password" "Password: ")
  (password-field {:class "stylized"} "password" password))

(defn valid? [{:keys [username password]}]
  (ah/admin-login? username password))

(defn store-redirect [{:keys [username password]}]
  (session/put! :username username)
  (session/put! :uid (:uid (ah/admin-get-by-username username)))
  (response/redirect "/unanswered"))

(defn logged-in? []
  (if (nil? (session/get :username)) false true))

(defpage "/login" {:as user}
  (if (logged-in?)
    (response/redirect "/unanswered")
	    (layout/main
       [:h3 "Administrator login"]
		    (form-to [:post "/login"]
	        (admin-login user)
         [:br]
         (label {:class "left"} "name" "&nbsp;")
	        (submit-button {:class "ask-button"} "Log in")))))

(defpage [:post "/login"] {:as user}
  (if (valid? user)
    (store-redirect user)  
    (render "/login" user)))

;; Unanswered questions page

(defn logout [] 
  (session/remove! :username))

(defpartial question-block [{:keys [qid author question answer date type uid] :as post}]
	(when post
	  [:li.post.hline-top
    [:div.left (image {:class "icon"} (str "/img/" type ".png"))]
	   [:div.headline "Asked on "
	    [:span.date.blue date] " by "
	    [:span.author.blue author] " in category "
      [:span.type.blue type] ":"]
    [:div.clear]
     [:div.text question]
    [:div (link-to {:class "answer-link"} (str "/answer/" qid) "Answer")]
    [:div.clear]]))

(defn build-page [questions]
  (layout/main
    [:h3 "Unanswered questions:"]
    [:ul.questions.hline-bottom
     (map question-block questions)]
    [:div (link-to {:class "answer-link"} "/logout" "Logut")]))

(defpage "/unanswered" []
  (if (not (logged-in?))
    (response/redirect "/login")
    (build-page (qh/get-unanswered))))

(defpage "/logout" []
  (logout)
  (response/redirect "/"))


;; Answer page

(defpage "/answer/:id" {:keys [id answer]}
  (if (not (logged-in?))
      (response/redirect "/login")
      (let [question (qh/get-by-id id)]
        (layout/main
          [:h3 "Answer this question: "]
          (form-to [:post (str "/answer/" id answer)]
                   [:ul.questions
                   [:li.post.hline-top
                    [:div "Asked on "
                     [:span.date.blue (:date question)] " by "
                     [:span.author.blue (:author question)] " in category "
                     [:span.type.blue (:type question)] ":"
                     [:div.text (:question question)]]]]
                   [:br]
                   (text-area {:class "answer-box"} "answer" answer)
                   (submit-button {:class "answer-button"} "Answer")
                   [:div.clear])))))

(defpage [:post "/answer/:id"] {:keys [id answer]}
  (qh/update id (session/get :uid) answer)  
    (response/redirect "/unanswered"))


