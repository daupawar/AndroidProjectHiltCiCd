package com.example.androidtemplatedsl.interactor.response

data class UserResponse(
    val `data`: List<User>,
    val page: Int=0,
    val per_page: Int=0,
    val total: Int=0,
    val total_pages: Int=0
)