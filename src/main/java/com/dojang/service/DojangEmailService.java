package com.dojang.service;

import com.dojang.model.ContactRequest;

public interface DojangEmailService {

    public void sendContactEmail(ContactRequest contactRequest);

}
