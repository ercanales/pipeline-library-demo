pipeline {
    agent any
    stages {
        stage('ChangeStrings') {
            steps {
                echo 'Main Step'
            }
        }
    }
    post {
        always {
            echo 'Test Post'
            step([$class: 'GitChangelogRecorder', config: [configFile: 'git-changelog-settings.json', createFileTemplateContent: '''<h1> Git Changelog changelog </h1>

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
            {{/tags}}
            ''', createFileTemplateFile: '', customIssues: [[link: '', name: '', pattern: '', title: ''], [link: '', name: '', pattern: '', title: '']], dateFormat: 'YYYY-MM-dd HH:mm:ss', file: 'CHANGELOG.html', fromReference: '', fromType: 'firstCommit', gitHubApi: '', gitHubApiTokenCredentialsId: '', gitHubIssuePattern: '#([0-9]+)', gitHubToken: '', gitLabApiTokenCredentialsId: '', gitLabProjectName: '', gitLabServer: '', gitLabToken: '', ignoreCommitsIfMessageMatches: '^\\[maven-release-plugin\\].*|^\\[Gradle Release Plugin\\].*|^Merge.*', ignoreTagsIfNameMatches: '', jiraIssuePattern: '\\b[a-zA-Z]([a-zA-Z]+)-([0-9]+)\\b', jiraPassword: '', jiraServer: '', jiraUsername: '', jiraUsernamePasswordCredentialsId: '', mediaWikiPassword: '', mediaWikiTemplateContent: '''__NOTOC__

            = Git Changelog changelog =
            Changelog of Git Changelog.

            {{#tags}}
            == {{name}} ==
             {{#issues}}
              {{#hasIssue}}
               {{#hasLink}}
            === {{name}} [{{link}} {{issue}}] {{title}} ===
               {{/hasLink}}
               {{^hasLink}}
            === {{name}} {{issue}} {{title}} ===
               {{/hasLink}}
              {{/hasIssue}}
              {{^hasIssue}}
            === {{name}} ===
              {{/hasIssue}}

               {{#commits}}
            [https://github.com/tomasbjerre/git-changelog-lib/commit/{{hash}} {{hash}}] {{authorName}} {{commitTime}}

            \'\'\'{{{messageTitle}}}\'\'\'

            {{#messageBodyItems}}
             * {{.}}
            {{/messageBodyItems}}

              {{/commits}}

             {{/issues}}
            {{/tags}}
            ''', mediaWikiTemplateFile: '', mediaWikiTitle: '', mediaWikiUrl: '', mediaWikiUsername: '', noIssueName: 'No issue', readableTagName: '/([^/]+?)$', showSummary: true, showSummaryTemplateContent: '''<h1> Git Changelog changelog </h1>

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
            {{/tags}}
            ''', showSummaryTemplateFile: '', showSummaryUseTemplateContent: true, subDirectory: '', timeZone: 'UTC', toReference: '', toType: 'master', untaggedName: 'Unreleased']])


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