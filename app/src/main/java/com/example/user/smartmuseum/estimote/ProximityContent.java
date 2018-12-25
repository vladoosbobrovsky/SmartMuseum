package com.example.user.smartmuseum.estimote;

//
// Running into any issues? Drop us an email to: contact@estimote.com
//

class ProximityContent {

    private String title;
    private String subtitle;



    ProximityContent(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }


    String getTitle() {
        return title;
    }

    String getSubtitle() {
        return subtitle;
    }

}
