package com.test.current.account.domain.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "UNIQUE_IDENTIFIER", unique = true)
    private String uniqueIdentifier;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "ADDRESS")
    private String address;

    @Setter
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column(name = "CREATED_DATE")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "UPDATED_DATE")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

}
