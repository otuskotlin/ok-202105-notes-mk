package info.javaway.enotty.backend.common.exceptions

import info.javaway.enotty.backend.common.models.EnottyStubCase

class EnottyStubCaseNotFound(stubCase: String) : Throwable("There is no matchable worker to handle case $stubCase") {
}