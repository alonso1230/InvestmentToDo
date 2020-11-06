package com.example.investmenttodo.ui.authorize.projects

import com.example.investmenttodo.app.App
import com.example.investmenttodo.base.BasePresenter
import com.example.investmenttodo.dataclass.Project
import com.example.investmenttodo.dataclass.ProjectData
import com.example.investmenttodo.manager.DataManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import moxy.InjectViewState

@InjectViewState
class ProjectsPresenter : BasePresenter<ProjectsView>() {

    private var projectList: MutableList<Project> = ArrayList()

    override fun onFirstViewAttach() {

//        val jsonString = "{\"projects\":[{\"name\":\"Строительство дорог\",\"openCards\":[{\"name\":\"Расширить дорогу на ул. Пушкина\",\"description\":\"Расширить проезжую часть на одну полосу в сторону центра города на ул. Пушкина, г. Симферополь\",\"createDate\":\"1602919468000\",\"resolveDate\":\"1603915471000\",\"deadlineDate\":\"1603965571000\",\"members\":[\"Oleg\",\"Ivan\",\"Misha\"],\"executorMember\":\"Ivan\",\"createMember\":\"Oleg\",\"comments\":[{\"text\":\"Взял в работу\",\"date\":\"1602919478000\",\"author\":\"Ivan\"},{\"text\":\"Закончил работу\",\"date\":\"1603915471000\",\"author\":\"Ivan\"}]},{\"name\":\"Положить асфальт на Московское шоссе\",\"description\":\"Положить асфальт на Московское шоссе, начиная с г. Симферополя до г. Армянск\",\"createDate\":\"1602919468000\",\"resolveDate\":\"1603915471000\",\"deadlineDate\":\"1603965571000\",\"members\":[\"Oleg\",\"Ivan\",\"Misha\"],\"executorMember\":\"Oleg\",\"createMember\":\"Oleg\",\"comments\":[{\"text\":\"Взял в работу\",\"date\":\"1602919478000\",\"author\":\"Ivan\"}]}],\"inProgressCards\":[{\"name\":\"Положить асфальт на ул. Гоголя\",\"description\":\"Положить асфальт на ул. Гоголя\",\"createDate\":\"1602919468000\",\"resolveDate\":\"1602919968000\",\"deadlineDate\":\"1602919998000\",\"members\":[\"Oleg\",\"Ivan\",\"Misha\"],\"executorMember\":\"Ivan\",\"createMember\":\"Oleg\",\"comments\":[]}],\"resolveCards\":[{\"name\":\"Положить асфальт на ул. Лермонтова\",\"description\":\"Положить асфальт на ул. Лермонтова\",\"createDate\":\"1602919468000\",\"resolveDate\":\"1602919968000\",\"deadlineDate\":\"1602919990008\",\"members\":[\"Oleg\",\"Ivan\",\"Misha\"],\"executorMember\":\"Ivan\",\"createMember\":\"Oleg\",\"comments\":[]}]},{\"name\":\"Разработка Android-приложения\",\"openCards\":[],\"inProgressCards\":[],\"resolveCards\":[]}]}"
//        App.instance.prefs.projectData = Gson().fromJson(jsonString, object : TypeToken<ProjectData>() {}.type)

        projectList = App.instance.prefs.projectData.projects
        DataManager.projectList = projectList
        if (projectList.isEmpty()) {
            viewState.showEmptyView()
        } else {
            viewState.setProjectsData(projectList)
        }
    }

    fun onItemClicked(position: Int) {
        DataManager.setCurrentProject(projectList[position])
        viewState.showProjectDetailScreen(projectList[position])
    }

}