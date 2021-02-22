package com.test.current.account.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "aResource")
public class TransactionsResource {

    private List<Transaction> items;
}
