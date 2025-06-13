package io.github.victorfasty.msCreditAppraiser.ex;

import lombok.Getter;

public class CommunicationErrorException extends Exception {

    @Getter
    private Integer status;




    public CommunicationErrorException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }


}
