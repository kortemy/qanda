(ns qanda.views.about
  (:require [qanda.views.layout :as common])
  (:use [noir.core :only [defpage]]))

(defpage "/answer" []
         (common/layout
           [:h1 "hello from answer"]))
