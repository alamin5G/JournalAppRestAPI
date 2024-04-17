package com.goonok.journalapp.entity;


import jdk.jfr.Relational;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
@Data //is equal to getter, setter, toString, and more..
@NoArgsConstructor //required for deserialization from json to pojo
public class JournalEntity {

    @Id
    private ObjectId id;
    @NonNull
    private String title;

    private String journal;
    private String notes;

    private LocalDateTime publishedDate;

}
