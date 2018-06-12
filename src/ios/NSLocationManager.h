//
//  NSLocationManager.h
//  GoodJob
//
//  Created by 梁仲太 on 2018/5/22.
//

#import <Foundation/Foundation.h>
#import <CoreLocation/CoreLocation.h>

typedef void (^MoLocationSuccess)(double lat,double lng,double alt);
typedef void(^MoLocationFailed) (NSError *error);

@interface NSLocationManager : NSObject<CLLocationManagerDelegate>{
    CLLocationManager *manager;
    MoLocationSuccess successCallBack;
    MoLocationFailed failedCallBack;
}

+ (NSLocationManager *) sharedGpsManager;

+ (void) getMoLocationWithSuccess:(MoLocationSuccess)success Failure:(MoLocationFailed)failure;

+ (void) stop;

@end
