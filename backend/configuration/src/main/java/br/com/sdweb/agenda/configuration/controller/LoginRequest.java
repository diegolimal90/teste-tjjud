package br.com.sdweb.agenda.configuration.controller;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank String username, 
        @NotBlank String password
) {}
