def call(body) {
  def pipelineParams = [:]
  body.resolveStrategy = Closure.DELEGATE_FIRST
  body.delegate = pipelineParams
  body()

  //def jenkinsAgent = pipelineParams?.agent ? "${pipelineParams.agent}" : "jenkins-agent"

  pipeline {
    agent none

    environment {
      APPLICATION_NAME = "${pipelineParams.app}"
    }

    stages {

      stage("Update Dev Objects") {
        agent any
        
        environment{
          PROJECT_SA_TOKEN = credentials("${pipelineParams.devProjectConfig.PROJECT}-jenkins-sa")
        }

        steps {

          subFlow(
            //PROJECT_CONFIG: "${pipelineParams.devProjectConfig}",
            SA_TOKEN: env.PROJECT_SA_TOKEN
          )

        } // steps
      } // stage
    }
  }
}
