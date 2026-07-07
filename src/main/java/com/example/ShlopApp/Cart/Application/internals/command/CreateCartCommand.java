package com.example.ShlopApp.Cart.Application.internals.command;

import com.example.ShlopApp.Cart.Domain.model.CartOwner;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.OwnerId;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;
//
//public record CreateCartCommand(
//        @JsonProperty("owner")
//        String owner
//) { }
public record CreateCartCommand(OwnerId owner) {}