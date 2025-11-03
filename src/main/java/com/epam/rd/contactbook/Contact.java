package com.epam.rd.contactbook;

public class Contact {

    private String contactName;
    private final ContactInfo nameInfo;
    private final ContactInfo[] emails = new ContactInfo[3];
    private final ContactInfo[] socials = new ContactInfo[5];
    private int sizeOfEmails = 0;
    private int sizeOfSocial = 0;
    private  ContactInfo phoneNumber;

    public Contact(String contactName) {
        this.contactName = contactName;

        this.nameInfo = new NameContactInfo();
    }

    private class NameContactInfo implements ContactInfo {

        @Override
        public String getTitle() {
            return "Name";
        }

        @Override
        public String getValue() {
            return Contact.this.contactName;
        }
    }

    public static class Email implements ContactInfo {

        private final String emailValue;

        public Email(String emailValue) {
            this.emailValue = emailValue;
        }

        @Override
        public String getTitle() {
            return "Email";
        }

        @Override
        public String getValue() {
            return emailValue;
        }
    }

    public static class Social implements ContactInfo {

        private final String socialTitle;
        private final String socialValue;

        public Social(String socialTitle, String socialValue) {
            this.socialTitle = socialTitle;
            this.socialValue = socialValue;
        }

        @Override
        public String getTitle() {
            return socialTitle;
        }

        @Override
        public String getValue() {
            return socialValue;
        }
    }


    public void rename(String newName) {
       if(newName != null && !newName.isBlank()) {
           this.contactName = newName;
       }
    }

    public Email addEmail(String localPart, String domain) {

        if (sizeOfEmails < 3) {
            String fullEmail;
            fullEmail = localPart + "@" + domain;

            Email newEmail = new Email(fullEmail);

            emails[sizeOfEmails] = newEmail;

            sizeOfEmails++;

            return newEmail;
        } else {
            return null;
        }

    }


    public Email addEpamEmail(String firstname, String lastname) {
        if (sizeOfEmails < 3) {
            String fullEmail;
            fullEmail = firstname + "_" + lastname + "@epam.com";

            Email newEmail = new Email(fullEmail){

                @Override
                public String getTitle(){
                    return "Epam Email";
                }
            };

            emails[sizeOfEmails] = newEmail;

            sizeOfEmails++;

            return newEmail;
        } else {
            return null;
        }

    }

    public ContactInfo addPhoneNumber(int code, String number) {
        if (phoneNumber == null) {
            phoneNumber = new ContactInfo() {

                @Override
                public String getTitle() {
                    return "Tel";
                }

                @Override
                public String getValue() {
                    return "+" + code + " " + number;
                }
            };

            return phoneNumber;
        } else {
            return null;
        }
    }

    public Social addTwitter(String twitterId) {
        if (sizeOfSocial < 5) {
            Social newTwitter = new Social("Twitter", twitterId);
            socials[sizeOfSocial] = newTwitter;

            sizeOfSocial++;

            return newTwitter;
        } else {
            return null;
        }
    }

    public Social addInstagram(String instagramId) {
        if (sizeOfSocial < 5) {
            Social newInstagram = new Social("Instagram", instagramId);
            socials[sizeOfSocial] = newInstagram;

            sizeOfSocial++;

            return newInstagram;
        } else {
            return null;
        }
    }

    public Social addSocialMedia(String title, String id) {
        if (sizeOfSocial < 5) {
            Social sm = new Social(title, id);
            socials[sizeOfSocial] = sm;

            sizeOfSocial++;

            return sm;
        } else {
            return null;
        }
    }


    public ContactInfo[] getInfo() {

        int totalSize = 1 + sizeOfEmails + sizeOfSocial;
        if (phoneNumber != null) {
            totalSize += 1;
        }
        int currentIndex = 0;

        ContactInfo[] infoArr = new ContactInfo[totalSize];

        infoArr[currentIndex] = this.nameInfo;
        currentIndex++;

        if(phoneNumber != null){
            infoArr[currentIndex] = phoneNumber;
            currentIndex++;
        }

        for (int i = 0; i < sizeOfEmails; i++){
            infoArr[currentIndex] = emails[i];
            currentIndex++;
        }

        for (int i = 0; i < sizeOfSocial; i++){
            infoArr[currentIndex] = socials[i];
            currentIndex++;
        }

        return infoArr;
    }
}



