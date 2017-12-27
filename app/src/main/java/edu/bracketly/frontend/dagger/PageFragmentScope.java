package edu.bracketly.frontend.dagger;

/**
 * Created by howor on 27.12.2017.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PageFragmentScope {

}
