(ns qanda.views.layout
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css html5]])
  (:use [noir.core :only [defpage]]))

(defpartial layout [& content]
            (html5
              [:head
               [:title "noir-qanda"]
               (include-css "/css/reset.css")]
              [:body
               [:div#wrapper
                content]]))
