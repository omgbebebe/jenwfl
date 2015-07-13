node {
	stage 'Build'

	checkout changelog: true, poll: true,
			scm: [$class: 'GitSCM',
			branches: [[name: "$GIT_BRANCH"]],
			browser: [$class: 'GitLab',
						repoUrl: "$GITLAB_DEFAULT_HTTP_URL" + '/' + "$PROJECT_NAME",
						version: "6.9"],
			doGenerateSubmoduleConfigurations: false,
//			extensions: [[$class: 'PerBuildTag']],
			submoduleCfg: [],
			userRemoteConfigs: [[url: "$GITLAB_DEFAULT_SSH_URL" + '/' + "$PROJECT_NAME"]]]

	echo('clonning jenkins-common repo')
	git branch: "$JENKINS_COMMON_BRANCH"
		, changelog: false
		, poll: false
		, url: "$GITLAB_DEFAULT_SSH_URL/$JENKINS_COMMON_REPO_NAME"

	echo('build via jenkins-common script')
	sh "$WORKSPACE/$JENKINS_COMMON_REPO_NAME/scripts/build/get_and_run_cicd_build.sh"

	echo('pack artifacts')
	archive '**/target/*.jar,**/target/*.tar.gz, down-stream-job.properties'

	if($PERFORM_DEPLOY) {
		stage 'Deploy'
		echo 'deploying'
	}

	if($PERFORM_TESTS) {
		stage 'ITests'
		echo 'testing'
	}

	stage 'UnDeploy'
	echo 'cleaning'
}
