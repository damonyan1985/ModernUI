#include "FlexLayout.h"

namespace yanbo {
// 限定: 只有子元素都是block才能使用flex布局
// 本引擎盒子与W3C的盒子模型计算略有不同，本引擎盒子设置的宽高
// width，height包含了边框+padding+内容的宽高，但不包含margin
// 而W3C中设置元素的width，height只包含了内容宽高，
// W3C真实的盒子宽高需要加上margin+border+padding+内容宽高
// 本引擎内容宽高是需要计算才能得出的


LVoid FlexLayout::flexRowLayout(HtmlView* view)
{
    const HtmlViewList& list = view->getChildren();
    HtmlViewList::Iterator iter = list.begin();
    HtmlViewList::Iterator iterEnd = list.end();
    LInt x = view->getStyle()->padding().leftPadding;
    LInt deltaY = view->getStyle()->border().topWidth + view->getStyle()->padding().topPadding;
    BOYIA_LOG("layoutInlineBlock, child size=%d", list.count());
    for (; iter != iterEnd; ++iter) {
        HtmlView* child = (*iter);
        x += child->getStyle()->margin().leftMargin;
        LInt y = deltaY + child->getStyle()->margin().topMargin;
        child->setPos(x, y);
        BOYIA_LOG("layoutInlineBlock, x=%d, y=%d", x, y);
        child->layout();
        x += child->getWidth() + child->getStyle()->margin().rightMargin;
        if (view->getHeight() < child->getHeight() + y) {
            view->setHeight(child->getHeight() + y);
        }
    }
    
    // 如果style中没有设置宽度，则flex布局宽高根据元素自己的宽高来限定
    if (!view->getStyle()->width) {
        view->setWidth(x);
    }
}

LVoid FlexLayout::flexRowReverse(HtmlView* view)
{
    const HtmlViewList& list = view->getChildren();
    HtmlViewList::Iterator iter = list.begin();
    HtmlViewList::Iterator iterEnd = list.end();
    LInt x = view->getWidth() - view->getStyle()->padding().rightPadding;
    LInt deltaY = view->getStyle()->border().topWidth + view->getStyle()->padding().topPadding;
    BOYIA_LOG("layoutInlineBlock, child size=%d", list.count());
    for (; iter != iterEnd; ++iter) {
        HtmlView* child = (*iter);
        x -= child->getStyle()->margin().rightMargin;
        LInt y = deltaY + child->getStyle()->margin().topMargin;
        child->setPos(x, y);
        BOYIA_LOG("layoutInlineBlock, x=%d, y=%d", x, y);
        child->layout();
        x -= child->getWidth() + child->getStyle()->margin().leftMargin;
        if (view->getHeight() < child->getHeight() + y) {
            view->setHeight(child->getHeight() + y);
        }
    }
}

// margin将失效，忽略margin的作用
LVoid FlexLayout::flexRowSpacebetween(HtmlView* view)
{
    const HtmlViewList& list = view->getChildren();
    if (list.count() == 0) {
        return;
    }
    
    if (list.count() == 1) {
        HtmlViewList::Iterator iter = list.begin();
        (*iter)->setPos(0, 0);
        return;
    }
    
    HtmlViewList::Iterator iter = list.begin();
    HtmlViewList::Iterator iterEnd = list.end();
    
    LInt childWidth = 0;
    LInt childHeight = 0;
    for (; iter != iterEnd; ++iter) {
        HtmlView* child = (*iter);
        child->layout();
        childWidth += child->getWidth();
        
        if (childHeight < child->getHeight()) {
            childHeight = child->getHeight();
        }
    }
    
    // style中没有指定height，则默认为子元素布局后的高度
    if (!view->getStyle()->height) {
        view->setHeight(childHeight);
    }
    
    LInt deltaMargin = (view->getWidth() - childWidth) / (list.count() - 1);
    LInt x = 0;
    iter = list.begin();
    for (; iter != iterEnd; ++iter) {
        HtmlView* child = (*iter);
        child->setXpos(x);
        x += child->getWidth() + deltaMargin;
    }
}

// 从上到下使用列排版
LVoid FlexLayout::flexColumnLayout(HtmlView* view)
{
    const HtmlViewList& list = view->getChildren();
    HtmlViewList::Iterator iter = list.begin();
    HtmlViewList::Iterator iterEnd = list.end();
    LInt x = view->getStyle()->padding().leftPadding;
    LInt y = view->getStyle()->border().topWidth + view->getStyle()->padding().topPadding;
    BOYIA_LOG("layoutInlineBlock, child size=%d", list.count());
    for (; iter != iterEnd; ++iter) {
        HtmlView* child = (*iter);
        //LInt x = child->getStyle()->margin().leftMargin;
        y += child->getStyle()->margin().topMargin;
        child->setPos(x, y);
        BOYIA_LOG("layoutInlineBlock, x=%d, y=%d", x, y);
        child->layout();
        y += child->getHeight() + child->getStyle()->margin().bottomMargin;
        if (view->getWidth() < child->getWidth() + x) {
            view->setWidth(child->getWidth() + x);
        }
    }
    
    // 如果style中没有设置宽度，则flex布局宽高根据元素自己的宽高来限定
    if (!view->getStyle()->height) {
        view->setHeight(x);
    }
}
// 从下到上使用列排版
LVoid FlexLayout::flexColumnReverseLayout(HtmlView* view)
{
    
}
// 等间距列排版
LVoid FlexLayout::flexColumnSpacebetween(HtmlView* view)
{
    
}
}
