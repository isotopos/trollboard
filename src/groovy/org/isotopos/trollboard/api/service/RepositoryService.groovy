package org.isotopos.trollboard.api.service

import org.isotopos.trollboard.api.Label
import org.isotopos.trollboard.api.Lane
import org.isotopos.trollboard.api.Milestone
import org.isotopos.trollboard.api.Project

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

  List<Label> getLabels(String token, String user, String projectId)

  List<Lane> getLanes(String token, String user, String projectId)

  void createHook(String token, String user, String projectId)
}
