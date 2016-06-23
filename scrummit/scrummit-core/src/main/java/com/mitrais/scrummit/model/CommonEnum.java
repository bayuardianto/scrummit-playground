package com.mitrais.scrummit.model;

/**
 * Created by Andreanus_P on 5/27/2016.
 */
public class CommonEnum {

    public enum ProjectStatus {
        CREATED,
        IN_PROGRESS,
        FINISHED
    }

    public enum ProjectRole {
        PRODUCT_OWNER,
        SCRUM_MASTER,
        MEMBER
    }
    
    public enum LogItem {
        PROJECT(1),ITERATION(2),CARD(3),TASK(4);

        public int value;

        private LogItem(int value){
            this.value = value;
        }
    }

    public enum LogType {
        CREATE(1),UPDATE(2),DELETE(3);

        public int value;

        private LogType(int value){
            this.value = value;
        }
    }
}
