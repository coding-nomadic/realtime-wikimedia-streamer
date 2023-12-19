package com.wikimedia.entities;

import com.wikimedia.models.Meta;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collection = "data")
@Setter
@Getter
public class WikiMediaData {
    @Id
    private String id;
    private Meta meta;
    private String type;
    private Integer namespace;
    private String title;
    private String titleUrl;
    private String comment;
    private Integer timestamp;
    private String user;
    private Boolean bot;
    private String notifyUrl;
    private String serverUrl;
    private String serverName;
    private String serverScriptPath;
    private String wiki;
    private String parsedcomment;


}
