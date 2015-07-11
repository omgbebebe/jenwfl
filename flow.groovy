node {
	stage 'Build'

	checkout changelog: true, poll: true,
			scm: [$class: 'GitSCM',
			branches: [[name: "$GIT_BRANCH"]],
			browser: [$class: 'GitLab',
						repoUrl: "$GITLAB_DEFAULT_HTTP_URL" + '/' + "$PROJECT_NAME",
						version: "6.9"],
			doGenerateSubmoduleConfigurations: false,
			extensions: [[$class: 'PerBuildTag']],
			submoduleCfg: [],
			userRemoteConfigs: [[url: "$GITLAB_DEFAULT_SSH_URL" + '/' + "$PROJECT_NAME"]]]

	stage 'Deploy'
	echo 'deploying'

	stage 'ITests'
	echo 'testing'

	stage 'UnDeploy'
	echo 'cleaning'
}
