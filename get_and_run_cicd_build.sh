#!/bin/bash

function die(){
    echo $1 >&2;
    exit 1;
}

function ensure(){
	[[ -z ${!1+x} ]] && die "$1 is unset"
}

ensure "NEXUS_URL"
ensure "CI_CD_NEXUS_REPOSITORY"
ensure "CI_CD_GROUP_ID"
ensure "CI_CD_VERSION"
ensure "WORKSPACE"
ensure "GIT_BRANCH"

wget "${NEXUS_URL}/nexus/service/local/artifact/maven/content?r=${CI_CD_NEXUS_REPOSITORY}&g=${CI_CD_GROUP_ID}&e=tar.gz&a=ci-cd&v=${CI_CD_VERSION}" --content-disposition --directory-prefix=./

tar -xf *.tar.gz --strip-components=1

${WORKSPACE}/ci-cd/bash/cicd.sh build -b ${GIT_BRANCH}
