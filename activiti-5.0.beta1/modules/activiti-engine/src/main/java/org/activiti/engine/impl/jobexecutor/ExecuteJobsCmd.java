/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.engine.impl.jobexecutor;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.runtime.JobEntity;


/**
 * @author Tom Baeyens
 */
public class ExecuteJobsCmd implements Command<Object> {

  private static Logger log = Logger.getLogger(ExecuteJobsCmd.class.getName());
  
  protected String jobId;

  public ExecuteJobsCmd(String jobId) {
    this.jobId = jobId;
  }

  public Object execute(CommandContext commandContext) {
    if (log.isLoggable(Level.FINE)) {
      log.fine("Executing job " + jobId);
    }
    JobEntity job = commandContext.getRuntimeSession().findJobById(jobId);
    
    if (job == null) {
      throw new ActivitiException("No job found for jobId '" + jobId + "'");
    }
    
    job.execute(commandContext);
    return null;
  }
}