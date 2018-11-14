pipeline {
    agent any
    stages {
        stage('ChangeStrings') {
            steps {
                  getChangeString()

                  echo gitChangelog returnType: 'STRING', template: '''<h1> Git Changelog changelog </h1>

                                                        <p>
                                                        Changelog of Git Changelog.
                                                        </p>

                                                        {{#tags}}
                                                        <h2> {{name}} </h2>
                                                         {{#issues}}
                                                          {{#hasIssue}}
                                                           {{#hasLink}}
                                                        <h2> {{name}} <a href="{{link}}">{{issue}}</a> {{title}} </h2>
                                                           {{/hasLink}}
                                                           {{^hasLink}}
                                                        <h2> {{name}} {{issue}} {{title}} </h2>
                                                           {{/hasLink}}
                                                          {{/hasIssue}}
                                                          {{^hasIssue}}
                                                        <h2> {{name}} </h2>
                                                          {{/hasIssue}}


                                                           {{#commits}}
                                                        <a href="https://github.com/tomasbjerre/git-changelog-lib/commit/{{hash}}">{{hash}}</a> {{authorName}} <i>{{commitTime}}</i>
                                                        <p>
                                                        <h3>{{{messageTitle}}}</h3>

                                                        {{#messageBodyItems}}
                                                         <li> {{.}}</li>
                                                        {{/messageBodyItems}}
                                                        </p>


                                                          {{/commits}}

                                                         {{/issues}}
                                                        {{/tags}}'''

            }
        }
    }
    post {
        always {
            echo 'Test Post'
            script {
                def changelogString = gitChangelog returnType: 'STRING', template: '''<h1> Git Changelog changelog </h1>

                                      <p>
                                      Changelog of Git Changelog.
                                      </p>

                                      {{#tags}}
                                      <h2> {{name}} </h2>
                                       {{#issues}}
                                        {{#hasIssue}}
                                         {{#hasLink}}
                                      <h2> {{name}} <a href="{{link}}">{{issue}}</a> {{title}} </h2>
                                         {{/hasLink}}
                                         {{^hasLink}}
                                      <h2> {{name}} {{issue}} {{title}} </h2>
                                         {{/hasLink}}
                                        {{/hasIssue}}
                                        {{^hasIssue}}
                                      <h2> {{name}} </h2>
                                        {{/hasIssue}}


                                         {{#commits}}
                                      <a href="https://github.com/tomasbjerre/git-changelog-lib/commit/{{hash}}">{{hash}}</a> {{authorName}} <i>{{commitTime}}</i>
                                      <p>
                                      <h3>{{{messageTitle}}}</h3>

                                      {{#messageBodyItems}}
                                       <li> {{.}}</li>
                                      {{/messageBodyItems}}
                                      </p>


                                        {{/commits}}

                                       {{/issues}}
                                      {{/tags}}'''

                echo changelogString
            }
        }
    }
}

def getChangeString() {
  def changeLogSets = currentBuild.changeSets
    for (int i = 0; i < changeLogSets.size(); i++) {
        def entries = changeLogSets[i].items
        for (int j = 0; j < entries.length; j++) {
            def entry = entries[j]
            echo "${entry.commitId} by ${entry.author} on ${new Date(entry.timestamp)}: ${entry.msg}"
            def files = new ArrayList(entry.affectedFiles)
            for (int k = 0; k < files.size(); k++) {
                def file = files[k]
                echo "  ${file.editType.name} ${file.path}"
            }
        }
    }
}