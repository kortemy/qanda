(ns qanda.views.layout
  (use noir.core
       hiccup.core
       hiccup.page-helpers))

(def main-links [{:url "/" :text "Ask"}
                 {:url "/questions" :text "Questions"}
                 {:url "/login" :text "Admin"}
                 {:url "/about" :text "About"}])

(def includes {:jquery (include-js "http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js")
               :default (include-css "/css/default.css")
               :js (include-js "/js/main.js")})

(defpartial build-head [incls]
            [:head
             [:title "Questions and Answers"]
             (map #(get includes %) incls)])

(defpartial link-item [{:keys [url text]}]
            [:li
             (link-to url text)])

(defpartial main [& content]
            (html5
              (build-head [:default :jquery :js])
              [:body
               [:div#wrapper
                [:div.content
                 [:div#header
                  [:h1 (link-to "/" "QandA")]
                  [:div.navi-wrap
                  [:div.table
                  [:ul.nav
                   (map link-item main-links)]]]]
                 content]]]))