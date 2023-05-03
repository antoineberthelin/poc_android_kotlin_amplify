# poc_android_kotlin_amplify

POC to describe the problem on save // operation

highlighting a problem related to multiple requests in parallel using amplify android kotlin datastore version 2.8.2

Context
emulator device 'Nexus_10_API_33
After 46 successfull save, the following exception is present and remain 

```
2023-05-03 17:10:10.067 12199-12300 DataStore               com.appsonic.poc.amplify             I  TODO_END 46
2023-05-03 17:10:10.067 12199-12343 amplify:aws-datastore   com.appsonic.poc.amplify             I  DataStore plugin initialized.
2023-05-03 17:10:10.069 12199-12343 amplify:aws-datastore   com.appsonic.poc.amplify             I  Orchestrator lock released.
2023-05-03 17:10:10.069 12199-12298 amplify:aws-datastore   com.appsonic.poc.amplify             I  Orchestrator lock acquired.
2023-05-03 17:10:10.069 12199-12342 amplify:aws-datastore   com.appsonic.poc.amplify             I  DataStore plugin initialized.
2023-05-03 17:10:10.070 12199-12255 amplify:aws-datastore   com.appsonic.poc.amplify             I  Orchestrator lock acquired.
2023-05-03 17:10:10.070 12199-12342 amplify:aws-datastore   com.appsonic.poc.amplify             I  Orchestrator lock released.
2023-05-03 17:10:10.071 12199-12360 amplify:aws-datastore   com.appsonic.poc.amplify             I  Success on attempt #1
2023-05-03 17:10:10.071 12199-12339 amplify:aws-datastore   com.appsonic.poc.amplify             I  DataStore plugin initialized.
2023-05-03 17:10:10.072 12199-12255 DataStoreException      com.appsonic.poc.amplify             E  Saved new question failed.
DataStoreException{message=Failed to start DataStore., cause=android.net.ConnectivityManager$TooManyRequestsException, recoverySuggestion=Retry.}
at com.amplifyframework.datastore.AWSDataStorePlugin.lambda$start$12(AWSDataStorePlugin.java:330)
at com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda34.accept(Unknown Source:4)
at io.reactivex.rxjava3.internal.observers.CallbackCompletableObserver.onError(CallbackCompletableObserver.java:64)
at io.reactivex.rxjava3.internal.operators.completable.CompletableSubscribeOn$SubscribeOnObserver.onError(CompletableSubscribeOn.java:74)
at io.reactivex.rxjava3.internal.operators.completable.CompletableAndThenCompletable$NextObserver.onError(CompletableAndThenCompletable.java:104)
at io.reactivex.rxjava3.internal.operators.completable.CompletableAndThenCompletable$SourceObserver.onError(CompletableAndThenCompletable.java:62)
at io.reactivex.rxjava3.internal.operators.completable.CompletablePeek$CompletableObserverImplementation.onError(CompletablePeek.java:95)
at io.reactivex.rxjava3.internal.operators.completable.CompletableFromAction.subscribeActual(CompletableFromAction.java:40)
at io.reactivex.rxjava3.core.Completable.subscribe(Completable.java:2850)
at io.reactivex.rxjava3.internal.operators.completable.CompletablePeek.subscribeActual(CompletablePeek.java:51)
at io.reactivex.rxjava3.core.Completable.subscribe(Completable.java:2850)
at io.reactivex.rxjava3.internal.operators.completable.CompletableAndThenCompletable.subscribeActual(CompletableAndThenCompletable.java:35)
at io.reactivex.rxjava3.core.Completable.subscribe(Completable.java:2850)
at io.reactivex.rxjava3.internal.operators.completable.CompletableAndThenCompletable$SourceObserver.onComplete(CompletableAndThenCompletable.java:67)
at io.reactivex.rxjava3.internal.operators.completable.CompletablePeek$CompletableObserverImplementation.onComplete(CompletablePeek.java:115)
at io.reactivex.rxjava3.internal.operators.completable.CompletablePeek$CompletableObserverImplementation.onComplete(CompletablePeek.java:115)
at io.reactivex.rxjava3.internal.operators.completable.CompletableSubscribeOn$SubscribeOnObserver.onComplete(CompletableSubscribeOn.java:79)
at io.reactivex.rxjava3.internal.operators.completable.CompletableTimeout$TimeOutObserver.onComplete(CompletableTimeout.java:87)
at io.reactivex.rxjava3.internal.operators.completable.CompletableFromAction.subscribeActual(CompletableFromAction.java:47)
at io.reactivex.rxjava3.core.Completable.subscribe(Completable.java:2850)
at io.reactivex.rxjava3.internal.operators.completable.CompletableTimeout.subscribeActual(CompletableTimeout.java:53)
at io.reactivex.rxjava3.core.Completable.subscribe(Completable.java:2850)
at io.reactivex.rxjava3.internal.operators.completable.CompletableSubscribeOn$SubscribeOnObserver.run(CompletableSubscribeOn.java:64)
at io.reactivex.rxjava3.core.Scheduler$DisposeTask.run(Scheduler.java:614)
at io.reactivex.rxjava3.internal.schedulers.ScheduledRunnable.run(ScheduledRunnable.java:65)
at io.reactivex.rxjava3.internal.schedulers.ScheduledRunnable.call(ScheduledRunnable.java:56)
at java.util.concurrent.FutureTask.run(FutureTask.java:264)
at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:307)
at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1137)
at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:637)
at java.lang.Thread.run(Thread.java:1012)
Caused by: android.net.ConnectivityManager$TooManyRequestsException
at android.net.ConnectivityManager.convertServiceException(ConnectivityManager.java:4032)
at android.net.ConnectivityManager.sendRequestForNetwork(ConnectivityManager.java:4221)
at android.net.ConnectivityManager.registerDefaultNetworkCallbackForUid(ConnectivityManager.java:4750)
at android.net.ConnectivityManager.registerDefaultNetworkCallback(ConnectivityManager.java:4717)
2023-05-03 17:10:10.072 12199-12255 DataStoreException      com.appsonic.poc.amplify             E  	at android.net.ConnectivityManager.registerDefaultNetworkCallback(ConnectivityManager.java:4691)
at com.amplifyframework.datastore.syncengine.DefaultConnectivityProvider.registerDefaultNetworkCallback(ReachabilityMonitor.kt:122)
at com.amplifyframework.datastore.syncengine.ReachabilityMonitorImpl.configure$lambda-0(ReachabilityMonitor.kt:66)
at com.amplifyframework.datastore.syncengine.ReachabilityMonitorImpl.$r8$lambda$0ktcpXUA7mmlJDYNYxufcF23hZY(Unknown Source:0)
at com.amplifyframework.datastore.syncengine.ReachabilityMonitorImpl$$ExternalSyntheticLambda0.subscribe(Unknown Source:6)
at io.reactivex.rxjava3.internal.operators.observable.ObservableCreate.subscribeActual(ObservableCreate.java:40)
at io.reactivex.rxjava3.core.Observable.subscribe(Observable.java:13099)
at io.reactivex.rxjava3.internal.operators.observable.ObservableSubscribeOn$SubscribeTask.run(ObservableSubscribeOn.java:96)
... 8 more
2023-05-03 17:10:10.072 12199-12240 amplify:aws-datastore   com.appsonic.poc.amplify             I  Orchestrator lock acquired.
2023-05-03 17:10:10.072 12199-12255 DataStoreException      com.appsonic.poc.amplify             E  Saved new question failed.
DataStoreException{message=Failed to start DataStore., cause=android.net.ConnectivityManager$TooManyRequestsException, recoverySuggestion=Retry.}
at com.amplifyframework.datastore.AWSDataStorePlugin.lambda$start$12(AWSDataStorePlugin.java:330)
at com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda34.accept(Unknown Source:4)
at io.reactivex.rxjava3.internal.observers.CallbackCompletableObserver.onError(CallbackCompletableObserver.java:64)
at io.reactivex.rxjava3.internal.operators.completable.CompletableSubscribeOn$SubscribeOnObserver.onError(CompletableSubscribeOn.java:74)
at io.reactivex.rxjava3.internal.operators.completable.CompletableAndThenCompletable$NextObserver.onError(CompletableAndThenCompletable.java:104)
at io.reactivex.rxjava3.internal.operators.completable.CompletableAndThenCompletable$SourceObserver.onError(CompletableAndThenCompletable.java:62)
at io.reactivex.rxjava3.internal.operators.completable.CompletablePeek$CompletableObserverImplementation.onError(CompletablePeek.java:95)
at io.reactivex.rxjava3.internal.operators.completable.CompletableFromAction.subscribeActual(CompletableFromAction.java:40)
at io.reactivex.rxjava3.core.Completable.subscribe(Completable.java:2850)
at io.reactivex.rxjava3.internal.operators.completable.CompletablePeek.subscribeActual(CompletablePeek.java:51)
at io.reactivex.rxjava3.core.Completable.subscribe(Completable.java:2850)
at io.reactivex.rxjava3.internal.operators.completable.CompletableAndThenCompletable.subscribeActual(CompletableAndThenCompletable.java:35)
at io.reactivex.rxjava3.core.Completable.subscribe(Completable.java:2850)
at io.reactivex.rxjava3.internal.operators.completable.CompletableAndThenCompletable$SourceObserver.onComplete(CompletableAndThenCompletable.java:67)
at io.reactivex.rxjava3.internal.operators.completable.CompletablePeek$CompletableObserverImplementation.onComplete(CompletablePeek.java:115)
at io.reactivex.rxjava3.internal.operators.completable.CompletablePeek$CompletableObserverImplementation.onComplete(CompletablePeek.java:115)
at io.reactivex.rxjava3.internal.operators.completable.CompletableSubscribeOn$SubscribeOnObserver.onComplete(CompletableSubscribeOn.java:79)
at io.reactivex.rxjava3.internal.operators.completable.CompletableTimeout$TimeOutObserver.onComplete(CompletableTimeout.java:87)
at io.reactivex.rxjava3.internal.operators.completable.CompletableFromAction.subscribeActual(CompletableFromAction.java:47)
at io.reactivex.rxjava3.core.Completable.subscribe(Completable.java:2850)
at io.reactivex.rxjava3.internal.operators.completable.CompletableTimeout.subscribeActual(CompletableTimeout.java:53)
at io.reactivex.rxjava3.core.Completable.subscribe(Completable.java:2850)
at io.reactivex.rxjava3.internal.operators.completable.CompletableSubscribeOn$SubscribeOnObserver.run(CompletableSubscribeOn.java:64)
at io.reactivex.rxjava3.core.Scheduler$DisposeTask.run(Scheduler.java:614)
at io.reactivex.rxjava3.internal.schedulers.ScheduledRunnable.run(ScheduledRunnable.java:65)
at io.reactivex.rxjava3.internal.schedulers.ScheduledRunnable.call(ScheduledRunnable.java:56)
at java.util.concurrent.FutureTask.run(FutureTask.java:264)
at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:307)
at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1137)
at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:637)
at java.lang.Thread.run(Thread.java:1012)
Caused by: android.net.ConnectivityManager$TooManyRequestsException
at android.net.ConnectivityManager.convertServiceException(ConnectivityManager.java:4032)
at android.net.ConnectivityManager.sendRequestForNetwork(ConnectivityManager.java:4221)
at android.net.ConnectivityManager.registerDefaultNetworkCallbackForUid(ConnectivityManager.java:4750)
at android.net.ConnectivityManager.registerDefaultNetworkCallback(ConnectivityManager.java:4717)
2023-05-03 17:10:10.072 12199-12255 DataStoreException      com.appsonic.poc.amplify             E  	at android.net.ConnectivityManager.registerDefaultNetworkCallback(ConnectivityManager.java:4691)
at com.amplifyframework.datastore.syncengine.DefaultConnectivityProvider.registerDefaultNetworkCallback(ReachabilityMonitor.kt:122)
at com.amplifyframework.datastore.syncengine.ReachabilityMonitorImpl.configure$lambda-0(ReachabilityMonitor.kt:66)
at com.amplifyframework.datastore.syncengine.ReachabilityMonitorImpl.$r8$lambda$0ktcpXUA7mmlJDYNYxufcF23hZY(Unknown Source:0)
at com.amplifyframework.datastore.syncengine.ReachabilityMonitorImpl$$ExternalSyntheticLambda0.subscribe(Unknown Source:6)
at io.reactivex.rxjava3.internal.operators.observable.ObservableCreate.subscribeActual(ObservableCreate.java:40)
at io.reactivex.rxjava3.core.Observable.subscribe(Observable.java:13099)
at io.reactivex.rxjava3.internal.operators.observable.ObservableSubscribeOn$SubscribeTask.run(ObservableSubscribeOn.java:96)
... 8 more
2023-05-03 17:10:10.072 12199-12317 amplify:aws-datastore   com.appsonic.poc.amplify             I  DataStore plugin initialized.
```



