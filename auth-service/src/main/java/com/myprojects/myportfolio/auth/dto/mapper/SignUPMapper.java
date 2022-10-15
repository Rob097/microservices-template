package com.myprojects.myportfolio.auth.dto.mapper;

import com.myprojects.myportfolio.auth.auth.DBUser;
import com.myprojects.myportfolio.auth.dto.SignUPRequest;
import com.myprojects.myportfolio.clients.general.Mapper;
import com.myprojects.myportfolio.clients.user.UserR;
import org.springframework.stereotype.Component;

@Component
public class SignUPMapper implements Mapper<DBUser, SignUPRequest> {
    @Override
    public DBUser map(SignUPRequest input) {
        return this.map(input, new DBUser());
    }

    @Override
    public DBUser map(SignUPRequest input, DBUser output) {
        if(input==null){
            return null;
        }
        if(output==null){
            output = new DBUser();
        }

        output.setFirstName(input.getFirstName());
        output.setLastName(input.getLastName());
        output.setEmail(input.getEmail());
        output.setPassword(input.getPassword());

        return output;
    }
}
