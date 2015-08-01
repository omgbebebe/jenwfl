stage 'Prepare'

def globals = getProps('global')
def project = getProps(PROJECT_NAME)
def props = new Properties()

props.putAll(globals)
props.putAll(project)

stage 'Build'

node{
	// Wipe out workspace
	sh 'rm -rf ./*'

	// FIXME: reclone flow repo to current workspace
//	git url: 'https://github.com/omgbebebe/jenwfl.git'

	withEnv(p2s(props)){
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
	for (k in p) { l << "${k}"}
	return l
}
