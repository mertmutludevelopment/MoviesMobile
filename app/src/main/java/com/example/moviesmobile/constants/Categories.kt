package com.example.moviesmobile.constants

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

data class CategoryItem(
    val name: String,
    val icon: ImageVector
)

object Categories {
    val categoryList = listOf(
        CategoryItem("All", Icons.Outlined.Movie),
        CategoryItem("Action", Icons.Outlined.LocalFireDepartment),
        CategoryItem("Drama", Icons.Outlined.TheaterComedy),
        CategoryItem("Science Fiction", Icons.Outlined.Rocket),
        CategoryItem("Fantastic", Icons.Outlined.AutoAwesome)
    )
} 