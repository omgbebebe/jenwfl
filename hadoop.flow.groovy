node{
	// Wipe out workspace
	sh 'rm -rf ./*'

	git url: 'https://github.com/omgbebebe/jenwfl.git'
	//archive '*.properties'

	def globals = getProps('global')
	def project = getProps(PROJECT_NAME)

	withEnv(p2s("globals)){
		sh "env"
	}
	switch(ACTION){
	case 'build':
		break
	case 'deploy':
		break
	case 'tests':
		break
	case 'undeploy':
		break
	}
}

def build(){
}

def deploy(){
}

def tests(){
}

def undeploy(){
}

def getProps(project){
	def str = readFile file: "${project}.properties", encoding : 'utf-8'
	def sr = new StringReader(str)
	def props = new Properties()
	props.load(sr)
	return props
//	def foobar = props.getProperty('somepropname')
}

def p2s(p){
	def l = []
	for (k in es) { l << "${k}"}
	return l
}
