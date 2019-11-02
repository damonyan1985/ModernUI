#ifndef ImageAndroid_h
#define ImageAndroid_h

#include "AutoObject.h"
#include "HtmlView.h"
#include "ImageLoadMap.h"
#include "LGdi.h"

namespace util {

struct JBitmapAndroid;
class ImageAndroid : public LImage, public yanbo::ImageClient {
public:
    ImageAndroid();
    virtual ~ImageAndroid();

public:
    void init(JNIEnv* env, jclass clzz, jobject obj);
    virtual LVoid load(const String& aPath, LVoid* image);
    virtual const String& url() const;
    virtual LVoid setLoaded(LBool loaded);
    void drawText(
        const String& text,
        const LRect& rect,
        LGraphicsContext::TextAlign align,
        const LFont& font,
        const LRgb& penColor,
        const LRgb& brushColor);

    AutoJObject getJavaBitmap() const;
    AutoJObject getBitmapObject() const;

    LVoid* item() const;
    LVoid setItem(yanbo::HtmlView* item);
    LVoid unlockPixels();
    LVoid* pixels() const;

    virtual LVoid onImageLoaded();

private:
    struct JBitmapAndroid* m_privateBitmap;
    yanbo::HtmlView* m_image;
    LVoid* m_pixels;
};
}

#endif
