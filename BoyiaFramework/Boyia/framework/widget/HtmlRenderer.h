#ifndef HtmlRenderer_h
#define HtmlRenderer_h

#include "CssParser.h"
#include "HtmlDocument.h"
#include "HtmlForm.h"

namespace yanbo {
class ResourceLoader;
class HtmlRenderer {
public:
    HtmlRenderer();
    ~HtmlRenderer();

public:
    // layout all html items
    void renderHTML(HtmlDocument* doc,
        const String& buffer,
        ResourceLoader* loader);

    void layout();
    util::CssManager* getCssManager() const;
    void paint(HtmlView* item);
    util::CssParser* getCssParser() const;

private:
    util::CssParser* m_cssParser;
    HtmlForm* m_form;
    HtmlDocument* m_htmlDoc;
    LRect m_rect;
    ResourceLoader* m_loader;
};
}
#endif
