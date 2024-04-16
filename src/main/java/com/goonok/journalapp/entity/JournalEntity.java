package com.goonok.journalapp.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
@Data //is equal to getter, setter, toString, and more..
public class JournalEntity {

    @Id
    private ObjectId id;
    private String title;
    private String journal;
    private String notes;

    private LocalDateTime publishedDate;


}
