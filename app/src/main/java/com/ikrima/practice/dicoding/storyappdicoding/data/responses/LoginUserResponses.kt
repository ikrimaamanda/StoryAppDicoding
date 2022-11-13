package com.ikrima.practice.dicoding.storyappdicoding.data.responses

data class LoginUserResponses(val error : String? = "",
                              val message : String? = "",
                              val loginResult : LoginUserResult
) {
    data class LoginUserResult(val userId : String? = "",
                               val name : String? = "",
                               val token : String? = ""
    )
}