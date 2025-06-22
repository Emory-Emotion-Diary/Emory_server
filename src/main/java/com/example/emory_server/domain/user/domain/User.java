package com.example.emory_server.domain.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15, nullable = false)
    private String userName;

    @Column(length = 20, nullable = false)
    private String accountId;

    @Column(length = 200, nullable = false)
    private String password;

    @Embedded
    private ClassInfo classInfo;

    public User(String accountId, String password, String userName, Integer grade, Integer classNum, Integer num) {
        this.accountId = accountId;
        this.password = password;
        this.userName = userName;
        this.classInfo = new ClassInfo(grade, classNum, num, String.format("%1d%1d%02d", grade, classNum, num));
    }

    @Getter
    @AllArgsConstructor
    @Embeddable
    public static class ClassInfo {
        private Integer grade;
        private Integer classNumber;
        private Integer number;
        private String schoolNumber;

        protected ClassInfo() {
        }
    }
}
