package com.example.investmenttodo.manager

import com.example.investmenttodo.app.App
import com.example.investmenttodo.app.Constants
import com.example.investmenttodo.dataclass.*

object DataManager {

    var userList: MutableList<User> = ArrayList()
    var projectList: MutableList<Project> = ArrayList()

    private var currentProject: Project? = null
    private var currentCard: Card? = null

    fun getUserByName(name: String): User {
        val userIndex = userList.indexOfFirst { it.name == name }
        return userList[if (userIndex == -1) 0 else userIndex]
    }

    fun getCurrentProject(): Project? = currentProject

    fun setCurrentProject(project: Project) {
        currentProject = project
    }

    fun getCurrentCard(): Card? = currentCard

    fun setCurrentCard(card: Card) {
        currentCard = card
    }

    fun getStatusCurrentCard(): String {
        if (currentProject?.openCards?.contains(currentCard) == true) return Constants.OPEN
        if (currentProject?.inProgressCards?.contains(currentCard) == true) return Constants.IN_PROGRESS
        return Constants.RESOLVE
    }

    fun setStatusCurrentCard(status: String) {
        when(status) {
            Constants.OPEN -> {
                if (currentProject?.inProgressCards?.contains(currentCard) == true) {
                    currentProject?.inProgressCards?.remove(currentCard)
                } else if (currentProject?.resolveCards?.contains(currentCard) == true) {
                    currentProject?.resolveCards?.remove(currentCard)
                }
                currentProject?.openCards?.add(currentCard!!)
            }
            Constants.IN_PROGRESS -> {
                if (currentProject?.openCards?.contains(currentCard) == true) {
                    currentProject?.openCards?.remove(currentCard)
                } else if (currentProject?.resolveCards?.contains(currentCard) == true) {
                    currentProject?.resolveCards?.remove(currentCard)
                }
                currentProject?.inProgressCards?.add(currentCard!!)
            }
            Constants.RESOLVE -> {
                if (currentProject?.openCards?.contains(currentCard) == true) {
                    currentProject?.openCards?.remove(currentCard)
                } else if (currentProject?.inProgressCards?.contains(currentCard) == true) {
                    currentProject?.inProgressCards?.remove(currentCard)
                }
                currentProject?.resolveCards?.add(currentCard!!)
            }
        }
        saveDataToPrefs()
    }

    fun addCommentToCurrentCard(comment: Comment) {
        currentCard?.comments?.add(comment)
        saveDataToPrefs()
    }

    private fun saveDataToPrefs() {
        App.instance.prefs.projectData = ProjectData(projectList)
    }
}