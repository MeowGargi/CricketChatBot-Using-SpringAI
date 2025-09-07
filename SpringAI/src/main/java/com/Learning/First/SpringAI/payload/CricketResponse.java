package com.Learning.First.SpringAI.payload;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@AllArgsConstructor
public class CricketResponse {
    private String content;

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }
}
