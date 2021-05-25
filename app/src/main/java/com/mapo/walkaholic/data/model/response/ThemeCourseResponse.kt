package com.mapo.walkaholic.data.model.response

data class ThemeCourseResponse(
        val code: String,
        val message: String,
        val data: DataThemeCourse
) {
        data class DataThemeCourse(
                val courseId : Int,
                val courseName : String,
                val courseTitle : String,
                val courseAddress : String,
                val courseTime : String,
                val courseDistance : String,
                val coursePoint : String
        )
}