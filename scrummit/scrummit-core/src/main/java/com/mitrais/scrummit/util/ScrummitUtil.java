package com.mitrais.scrummit.util;

import com.mitrais.scrummit.config.SMConstant;
import com.mitrais.scrummit.model.User;

public class ScrummitUtil {

    public static String generateTenantName(User user) {
        return SMConstant.SCRUMMIT_DB_TENANT_PREFIX + user.getUsername();
    }

}
