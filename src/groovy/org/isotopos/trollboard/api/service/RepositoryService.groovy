package org.isotopos.trollboard.api.service

import org.isotopos.trollboard.api.Project
import org.isotopos.trollboard.api.Milestone

/**
 * Created with IntelliJ IDEA.
 * User: domix
 * Date: 10/11/12
 * Time: 14:46
 * To change this template use File | Settings | File Templates.
 */
interface RepositoryService {
  List<Project> getProjects(String token)
  void addDefaultLabels(String token, String user, String proyectId)
  List<Milestone> getMilestones(String token, String user, String projectId)
}
