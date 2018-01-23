package edu.bracketly.frontend.app.signup;

import dagger.Binds;
import dagger.Module;
import edu.bracketly.frontend.app.BaseActivity;
import edu.bracketly.frontend.app.BaseActivityModule;
import edu.bracketly.frontend.dagger.ActivityScope;

/**
 * Created by howor on 23.01.2018.
 */

@Module(includes = {BaseActivityModule.class})
public abstract class SignUpActivityModule {
    @Binds
    @ActivityScope
    abstract BaseActivity bindBaseActivity(SignUpActivity signUpActivity);
}
